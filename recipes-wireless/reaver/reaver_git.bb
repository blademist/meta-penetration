SUMMARY = "A tool for attacking Wi-Fi Protected Setup (WPS) registrar PINs"
HOMEPAGE = "https://github.com/t6x/reaver-wps-fork-t6x.git"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../docs/LICENSE;md5=b8c7f7d7654b933d1bebc68cbdea7c05"

SRCREV = "144ca37f660ec0798ddfbe5a136e742772fb0455"
SRC_URI = "git://github.com/t6x/reaver-wps-fork-t6x.git"
PV = "1.6.4+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap sqlite3"
RDEPENDS_${PN} = "aircrack-ng pixiewps"

inherit autotools-brokensep

EXTRA_OEMAKE = "'CC=${CC}' \
                'AR=${AR}' \
                'RANLIB=${RANLIB}' \
                "

do_install () {
    mkdir -p ${D}${bindir}
    install -m 755 wash ${D}${bindir}
    install -m 755 reaver ${D}${bindir}
}

