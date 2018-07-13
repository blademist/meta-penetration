SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRCREV = "f813196b39f028b7fba4ec1f8b326aad0f6a33a2"
SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git"
PV = "1.6.5+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap"
RDEPENDS_${PN} = "pixiewps aircrack-ng"

inherit autotools-brokensep

do_install () {
    mkdir -p ${D}${bindir}
    install -m 755 wash ${D}${bindir}
    install -m 755 reaver ${D}${bindir}
}
