SUMMARY = "A tool for bruteforcing the WPS pin"
HOMEPAGE = "https://github.com/wiire/pixiewps"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=dedcfd78ca4eab2efdf6a4c5be1ab762"

SRCREV = "9e5bdc6c86c8487b2a6107d5ab3559ed5c738c59"
SRC_URI = "git://github.com/wiire/pixiewps.git"
PV = "1.4.2+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

EXTRA_OEMAKE = "PREFIX=${prefix} \
                OPENSSL=1 \
               "

do_install () {
    oe_runmake install DESTDIR=${D}
}

