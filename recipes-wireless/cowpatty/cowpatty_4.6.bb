SUMMARY = "Implementation of an offline dictionary attack against WPA/WPA2 networks using PSK-based authentication"
HOMEPAGE = "http://www.willhackforsushi.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://www.willhackforsushi.com/code/${BPN}/${PV}/${BP}.tgz"
SRC_URI[md5sum] = "b90fd36ad987c99e7cc1d2a05a565cbd"
SRC_URI[sha256sum] = "cd3fc113e5052d3ee08ab71aa87edf772d044f760670c73fde5d5581d7803bc2"

DEPENDS = "openssl libpcap"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "BINDIR=${bindir}"

do_install () {
    oe_runmake install DESTDIR=${D}
}

