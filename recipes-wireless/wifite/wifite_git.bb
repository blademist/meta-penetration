SUMMARY = "An automated wireless attack tool"
HOMEPAGE = "https://github.com/derv82/wifite"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://wifite.py;beginline=15;endline=18;md5=b32b09db3ee302aea4d7d6802ef0cd9e \
                    file://readme.md;beginline=69;endline=74;md5=00e631a7e00b5e631bdf4feef89fd909 \
                    "

SRCREV = "918a499786afdfefcc9582a29df79d0959294ba2"
SRC_URI = "git://github.com/derv82/wifite.git"
PV = "2.0r87+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/"

RDEPENDS_${PN} = "python python-subprocess python-argparse iw aircrack-ng cowpatty reaver"

do_install () {
    mkdir -p ${D}${bindir}
    install -m 755 ${S}wifite.py ${D}${bindir}
}

