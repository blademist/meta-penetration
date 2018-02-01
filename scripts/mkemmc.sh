#!/bin/sh
#
# Create 2 partitions on BBB eMMC
#

export LC_ALL=C

DEV="/dev/mmcblk1"
BOOTDEV="/dev/mmcblk1p1"
ROOTFSDEV="/dev/mmcblk1p2"

if [ $UID != "0" ];then
	echo "This script should be run by root"
	exit 1
fi

which sfdisk > /dev/null 2>&1
if [ $? -ne 0 ]; then
	echo "Command sfdisk not found!"
	exit 1
fi

echo -e "\nWorking on $DEV\n"

mount | grep -q $DEV

if [ $? -eq 0 ]; then
	echo "Found mounted eMMC partitions"
	echo "Aborting"
	exit 1
fi

SIZE=`fdisk -l $DEV | grep "Disk $DEV" | cut -d' ' -f5`

echo EMMC SIZE – $SIZE bytes

if [ "$SIZE" -lt 1800000000 ]; then
	echo "Require an eMMC of at least 2GB"
	exit 1
fi

echo -e "\nOkay, here we go ...\n"

echo -e "=== Zeroing the MBR ===\n"
dd if=/dev/zero of=$DEV bs=1024 count=1024
if [ $? -ne 0 ]; then
	echo "Fail to zero MBR on ${DEV}"
	exit 1
fi

# Minimum required 2 partitions
# Sectors are 512 bytes
# 0     : 64KB, no partition, MBR then empty
# 128   : 64MB, FAT partition, bootloader 
# 131200: 2GB+, linux partition, root filesystem

echo -e "\n=== Creating 2 partitions ===\n"
{
	echo 128,131072,0x0C,*
	echo 131200,+,0x83,-
} | sfdisk $DEV

if [ $? -ne 0 ]; then
	echo "Fail to create partitions on ${DEV}"
	exit 1
fi

sleep 1

mount | grep -q ${BOOTDEV}
if [ $? -eq 0 ]; then
	umount ${BOOTDEV}
	if [ $? -ne 0 ]; then
		echo "Fail to umount ${BOOTDEV}"
		exit 1
	fi
fi

mount | grep -q ${ROOTFSDEV}
if [ $? -eq 0 ]; then
	umount ${ROOTFSDEV}
	if [ $? -ne 0 ]; then
		echo "Fail to umount ${ROOTFSDEV}"
		exit 1
	fi
fi

echo -e "\nFormatting ${BOOTDEV} as fat\n"
mkfs.vfat -F 32 ${BOOTDEV} -n BOOT
if [ $? -ne 0 ]; then
	echo "Fail to format ${BOOTDEV}"
	exit 1
fi

echo -e "\nFormatting ${ROOTFSDEV} as ext4\n"
mkfs.ext4 -q -L ROOT ${ROOTFSDEV}
if [ $? -ne 0 ]; then
	echo "Fail to format ${ROOTFSDEV}"
	exit 1
fi

sleep 1
echo -e "\n=== Done! ===\n"

