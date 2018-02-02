SUMMARY = "A wireless security auditing and attack software program written using the Python Programming Language and the Python Qt GUI library"
HOMEPAGE = "https://github.com/savio-code/fern-wifi-cracker"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://core/wps.py;beginline=13;endline=26;md5=a0830c84aa087dd904e22395e93d16c2"

SRCREV = "d25e58e0f38dcb68724f4476f23992c0017cc255"
SRC_URI = "git://github.com/savio-code/fern-wifi-cracker.git \
           file://fern-wifi-cracker \
           "

PV = "2.3+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/Fern-Wifi-Cracker"

RDEPENDS_${PN} = "python python-pyqt python-sqlite3 python-json python-netclient python-scapy libicui18n xterm subversion macchanger aircrack-ng reaver"

do_install () {
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${datadir}/${BPN}
    install -m 755 ${WORKDIR}/fern-wifi-cracker ${D}${bindir}
    install -m 755 *.py ${D}${datadir}/${BPN}
    install -m 644 version ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/core ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/extras ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/gui ${D}${datadir}/${BPN}
    cp -rf --preserve=mode,links ${S}/resources ${D}${datadir}/${BPN}
}

