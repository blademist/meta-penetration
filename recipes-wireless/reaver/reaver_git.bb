SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git;branch=master;protocol=https"
SRCREV = "bd0f38262224c1b88ba9f1f95cb5476a488d2295"
PV = "1.6.6+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap"
RDEPENDS:${PN} = "pixiewps aircrack-ng"

inherit autotools-brokensep

do_install () {
    mkdir -p ${D}${bindir}
    install -m 755 wash ${D}${bindir}
    install -m 755 reaver ${D}${bindir}
}
