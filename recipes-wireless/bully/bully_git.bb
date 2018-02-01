SUMMARY = "An implementation of the WPS brute force attack"
HOMEPAGE = "https://github.com/Lrs121/bully.git"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://bully.c;beginline=4;endline=17;md5=33e042a5271c3fcd15b548278fcc65dc"

SRCREV = "c865f509acca6c5c62efc9db1c4df1894f75187d"
SRC_URI = "git://github.com/Lrs121/bully.git"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap openssl"
RDEPENDS_${PN} = "aircrack-ng"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "prefix=${prefix}"

do_install () {
    oe_runmake install DESTDIR=${D}
}

