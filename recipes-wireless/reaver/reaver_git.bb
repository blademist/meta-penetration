SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git;branch=master;protocol=https"
SRCREV = "4091bf2b31bf9fa7f1cfc913982a525f787ef990"
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
