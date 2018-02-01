#!/bin/sh
#
# Script to install system to the BBB eMMC
#
# This script should normally be run as
#
#  ./install_to_emmc.sh
#
# Assumes the following files are available in the local directory:
#
#   1) MLO
#   2) u-boot.img
#   3) uEnv.txt
#   4) core-image-pen-beaglebone.tar.bz2
#

export LC_ALL=C

MLO="MLO"
UBOOT="u-boot.img"
ROOTFS="core-image-pen-beaglebone.tar.bz2"
UENV="uEnv.txt"

BOOTDEV="/dev/mmcblk1p1"
ROOTFSDEV="/dev/mmcblk1p2"

MOUNT_BOOT_POINT="/media/boot"
MOUNT_ROOTFS_POINT="/media/rootfs"

SRCDIR=""

if [ $UID != "0" ];then
	echo "This script should be run by root"
	exit 1
fi

if [ $# -eq 1 ]; then
	ROOTFS="$1"
fi

if [ -z "${SRCDIR}" ]; then
	SRCDIR="./"
else
	if [ ! -d "${SRCDIR}" ]; then
		echo "Source directory not found: ${SRCDIR}"
		exit 1
	fi
fi

if [ ! -b ${BOOTDEV} ]; then
	echo "Block device not found: ${BOOTDEV}"
	exit 1
fi

if [ ! -b ${ROOTFSDEV} ]; then
	echo "Block device not found: ${ROOTFSDEV}"
	exit 1
fi

if [ ! -d ${MOUNT_BOOT_POINT} ]; then
	mkdir -p ${MOUNT_BOOT_POINT}
fi

if [ ! -d ${MOUNT_ROOTFS_POINT} ]; then
	mkdir -p ${MOUNT_ROOTFS_POINT}
fi

if [ ! -f ${SRCDIR}/${MLO} ]; then
	echo -e "File not found: ${SRCDIR}/${MLO}\n"
	exit 1
fi

if [ ! -f ${SRCDIR}/${UBOOT} ]; then
	echo -e "File not found: ${SRCDIR}/${UBOOT}\n"
	exit 1
fi

#if [ ! -f ${SRCDIR}/${UENV} ]; then
#        echo "File not found: ${SRCDIR}/${UENV}"
#        exit 1
#fi

if [ ! -f ${SRCDIR}/${ROOTFS} ]; then
	echo -e "File not found: ${SRCDIR}/${ROOTFS}\n"
	exit 1
fi

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

sleep 1

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

echo "Mounting $BOOTDEV"
mount ${BOOTDEV} ${MOUNT_BOOT_POINT}
if [ $? -ne 0 ]; then
	echo "Fail to mount $BOOTDEV\n"
	exit 1
fi

echo "Mounting $ROOTFSDEV"
mount ${ROOTFSDEV} ${MOUNT_ROOTFS_POINT}
if [ $? -ne 0 ]; then
	echo "Fail to mount $ROOTFSDEV\n"
	exit 1
fi

echo "Copying ${MLO}"
cp ${SRCDIR}/${MLO} ${MOUNT_BOOT_POINT}/${MLO}

echo "Copying ${UBOOT}"
cp ${SRCDIR}/${UBOOT} ${MOUNT_BOOT_POINT}/${UBOOT}

#echo "Copying ${UENV}"
#cp ${SRCDIR}/${UENV} ${MOUNT_BOOT_POINT}/${UENV}

echo "Extracting ${ROOTFS}"
tar xjf ${SRCDIR}/${ROOTFS} -C ${MOUNT_ROOTFS_POINT}
if [ $? -ne 0 ]; then
	echo "Fail to extract ${ROOTFS}\n"
	exit 1
fi

sync; sync; sync

echo "Unmounting ${BOOTDEV}"
umount ${BOOTDEV}
if [ $? -ne 0 ]; then
	echo "Fail to umount ${BOOTDEV}\n"
	exit 1
fi

echo "Unmounting ${ROOTFSDEV}"
umount ${ROOTFSDEV}
if [ $? -ne 0 ]; then
	echo "Fail to umount ${ROOTFSDEV}\n"
	exit 1
fi

echo "Done"

