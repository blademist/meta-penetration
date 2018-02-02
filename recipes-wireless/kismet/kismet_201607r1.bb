SUMMARY = "An 802.11 layer2 wireless network detector, sniffer, and intrusion detection system"
HOMEPAGE = "https://www.kismetwireless.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=13f9b058909ac5d915e914d8877fc463"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-2016-07-R1.tar.xz \
           file://0001-configure.ac-fix-searching-ncurses-headers.patch \
           file://0002-Makefile.inc.in-change-man-group-to-root.patch \
           "

SRC_URI[md5sum] = "7fa6e86c5078a0e7d91fc9bf954c5107"
SRC_URI[sha256sum] = "bdb21f153311f1ff3b16621bf0d6740f66369bf0982b0a289c9a12af8847e237"

S = "${WORKDIR}/${BPN}-2016-07-R1/"

DEPENDS = "libnl libpcap ncurses"
RDEPENDS_${PN} = "ncurses-libtinfo ncurses-libncurses ncurses-libpanel libpcre gpsd wireshark"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/${BPN}"

