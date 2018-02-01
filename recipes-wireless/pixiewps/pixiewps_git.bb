SUMMARY = "A tool for bruteforcing the WPS pin"
HOMEPAGE = "https://github.com/wiire/pixiewps"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://../LICENSE.md;md5=dedcfd78ca4eab2efdf6a4c5be1ab762"

SRCREV = "d5a7111097c32cb34f54ed64e3d6884b31a53761"
SRC_URI = "git://github.com/wiire/pixiewps.git"
PV = "1.2.2+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "openssl"

do_compile_prepend () {
    sed -i -e 's:LOCDIR = $(PREFIX)/local/bin:LOCDIR = $(PREFIX)/bin:g' ${S}/Makefile
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

