SUMMARY = "Tool to view/change network interface MAC addresses"
DESCRIPTION = "A GNU/Linux utility for viewing/manipulating the MAC address of network interfaces."
HOMEPAGE = "http://www.gnu.org/software/macchanger"
LICENSE  = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "net"

SRCREV = "f4f66e1eba1f5154a365d3323088050d0f75a168"
SRC_URI = "git://github.com/alobbs/macchanger.git;branch=master;protocol=https"
PV = "1.7.0"

S = "${WORKDIR}/git"

inherit autotools

