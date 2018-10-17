SUMMARY = "A complete re-write of wifite, a Python script for auditing wireless networks"
HOMEPAGE = "https://github.com/derv82/wifite2"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

SRCREV = "e190794149f488f9c4a2801962e5165b29e71b5e"
SRC_URI = "git://github.com/derv82/wifite2.git \
           file://wifite2 \
           "
PV = "2.2.5+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/"

RDEPENDS_${PN} = "python python-subprocess python-argparse iw wireless-tools aircrack-ng cowpatty reaver"

do_install () {
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${datadir}/${BPN}
    install -m 755 ${WORKDIR}/wifite2 ${D}${bindir}
    install -m 755 ${S}/Wifite.py ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/wifite ${D}${datadir}/${BPN}
}
