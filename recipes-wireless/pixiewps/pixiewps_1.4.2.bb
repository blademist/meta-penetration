SUMMARY = "A tool for bruteforcing the WPS pin"
HOMEPAGE = "https://github.com/wiire/pixiewps"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=dedcfd78ca4eab2efdf6a4c5be1ab762"

SRCREV = "28f68e694bdf5774300a1451c970578de0e19ce0"
SRC_URI = "git://github.com/wiire/pixiewps.git"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

EXTRA_OEMAKE = "PREFIX=${prefix} \
                OPENSSL=1 \
               "

do_install () {
    oe_runmake install DESTDIR=${D}
}

