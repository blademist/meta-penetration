SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRCREV = "2d6e537e609bad84996847ab5ea6b59bf4bc6773"
SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git"
PV = "1.6.6+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap"
RDEPENDS_${PN} = "pixiewps aircrack-ng"

inherit autotools-brokensep

do_install () {
    mkdir -p ${D}${bindir}
    install -m 755 wash ${D}${bindir}
    install -m 755 reaver ${D}${bindir}
}
