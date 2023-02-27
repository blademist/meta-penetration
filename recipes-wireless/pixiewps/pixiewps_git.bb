SUMMARY = "A tool for bruteforcing the WPS pin"
HOMEPAGE = "https://github.com/wiire/pixiewps"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=dedcfd78ca4eab2efdf6a4c5be1ab762"

SRC_URI = "git://github.com/wiire/pixiewps.git;branch=master;protocol=https"
SRCREV = "464326fbcc499ac92d4f04c3990a9cc9c5124e86"
PV = "1.4.2+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

EXTRA_OEMAKE = "PREFIX=${prefix} \
                OPENSSL=1 \
               "

do_install () {
    oe_runmake install DESTDIR=${D}
}

