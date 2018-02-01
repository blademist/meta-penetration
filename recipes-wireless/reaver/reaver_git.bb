SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRCREV = "6e60ee25e86ec798de2e23971b029d555e9dc398"
SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git"
PV = "1.5+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap sqlite3"
RDEPENDS_${PN} = "aircrack-ng pixiewps"

inherit autotools-brokensep

EXTRA_OEMAKE = "'CC=${CC}' \
                'AR=${AR}' \
                'RANLIB=${RANLIB}' \
                "

do_install () {
    mkdir -p ${D}${sysconfdir}/reaver
    install -m 644 reaver.db ${D}${sysconfdir}/reaver
    mkdir -p ${D}${bindir}
    install -m 755 wash ${D}${bindir}
    install -m 755 reaver ${D}${bindir}
}

