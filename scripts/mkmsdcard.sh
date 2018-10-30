#!/bin/bash
#
# Create 2 partitions on Micro SD card
#

export LANG=C
export LC_ALL=C

function ver() {
	printf "%03d%03d%03d" $(echo "$1" | tr '.' ' ')
}

if [ -n "$1" ]; then
	DRIVE=$1
else
	echo -e "\nUsage: sudo $0 <device>\n"
	echo -e "Example: sudo $0 /dev/sdc\n"
	exit 1
fi


echo -e "\nWorking on $DRIVE\n"

#make sure that the SD card isn't mounted before we start
if [ -b ${DRIVE}1 ]; then
	umount ${DRIVE}1
	umount ${DRIVE}2
elif [ -b ${DRIVE}p1 ]; then
	umount ${DRIVE}p1
	umount ${DRIVE}p2
else
	umount ${DRIVE}
fi


SIZE=`fdisk -l $DRIVE | grep "Disk $DRIVE" | cut -d' ' -f5`

echo DISK SIZE – $SIZE bytes

if [ "$SIZE" -lt 1800000000 ]; then
	echo "Require an SD card of at least 2GB"
	exit 1
fi

# new versions of sfdisk don't use rotating disk params
sfdisk_ver=`sfdisk --version | awk '{ print $4 }'`

if [ $(ver $sfdisk_ver) -lt $(ver 2.26.2) ]; then
	CYLINDERS=`echo $SIZE/255/63/512 | bc`
	echo "CYLINDERS – $CYLINDERS"
	SFDISK_CMD="sfdisk --force -D -uS -H255 -S63 -C ${CYLINDERS}"
else
	SFDISK_CMD="sfdisk"
fi

echo -e "\nOkay, here we go ...\n"

echo -e "=== Zeroing the MBR ===\n"
dd if=/dev/zero of=$DRIVE bs=1024 count=1024

# Minimum required 2 partitions
# Sectors are 512 bytes
# 0     : 64KB, no partition, MBR then empty
# 128   : 64 MB, FAT partition, bootloader 
# 131200: 2GB+, linux partition, root filesystem

echo -e "\n=== Creating 2 partitions ===\n"
{
	echo 128,131072,0x0C,*
	echo 131200,+,0x83,-
} | $SFDISK_CMD $DRIVE


sleep 1

# now make partitions.
echo -e "\n=== Formating 2 partitions ===\n"
if [ -b ${DRIVE}1 ]; then
	PARTITION1="${DRIVE}1"
	PARTITION2="${DRIVE}2"
elif [ -b ${DRIVE}p1 ]; then
	PARTITION1="${DRIVE}p1"
	PARTITION2="${DRIVE}p2"
else
	echo "Can not find partitions for ${DRIVE}"
	exit 0
fi

if [ -b ${PARTITION1} ]; then
	umount ${PARTITION1}
	mkfs.vfat -F 32 -n "boot" ${PARTITION1}
else
	echo "Can not find boot partition in /dev"
fi

if [ -b ${PARITION2} ]; then
	umount ${PARTITION2}
	mke2fs -j -L "rootfs" ${PARTITION2} 
else
	echo "Can not find rootfs partition in /dev"
fi

echo -e "\n=== Done! ===\n"

