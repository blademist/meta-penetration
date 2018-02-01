SUMMARY = "An 802.11 layer2 wireless network detector, sniffer, and intrusion detection system"
HOMEPAGE = "https://www.kismetwireless.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=13f9b058909ac5d915e914d8877fc463"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-2013-03-R1b.tar.xz \
           file://configure.in.patch \
           "

SRC_URI[md5sum] = "596acdd2940887dd05a498ea27475eea"
SRC_URI[sha256sum] = "636d4d7ef8c67ae6ee8d8e1635f5115700eecb9fa4c208afaee30238db527c2c"

S = "${WORKDIR}/${BPN}-2013-03-R1b/"

DEPENDS = "libnl libpcap ncurses"
RDEPENDS_${PN} = "ncurses-libtinfo ncurses-libncurses ncurses-libpanel libpcre gpsd wireshark"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/${BPN}"

