SUMMARY = "An implementation of the WPS brute force attack"
HOMEPAGE = "https://github.com/aanarchyy/bully"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://bully.c;beginline=4;endline=17;md5=33e042a5271c3fcd15b548278fcc65dc"

SRCREV = "3ab3bc830738f447dce112e8551e3ac8193bf521"
SRC_URI = "git://github.com/aanarchyy/bully.git"
PV = "1.1+git${SRCPV}"

S = "${WORKDIR}/git/src"

DEPENDS = "libpcap"
RDEPENDS_${PN} = "aircrack-ng"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "prefix=${prefix}"

do_install () {
    oe_runmake install DESTDIR=${D}
}

