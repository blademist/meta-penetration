SUMMARY = "A complete re-write of wifite, a Python script for auditing wireless networks"
HOMEPAGE = "https://github.com/derv82/wifite"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

SRCREV = "f24ec55999e78a6f1de543d8d75a8cd65a4676cf"
SRC_URI = "git://github.com/derv82/wifite2.git \
           file://wifite2 \
           "
PV = "2.1.6+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/"

RDEPENDS_${PN} = "python python-subprocess python-argparse iw aircrack-ng cowpatty reaver"

do_install () {
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${datadir}/${BPN}
    install -m 755 ${WORKDIR}/wifite2 ${D}${bindir}
    install -m 755 ${S}/Wifite.py ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/wifite ${D}${datadir}/${BPN}
}
