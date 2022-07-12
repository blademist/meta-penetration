SUMMARY = "A wireless security auditing and attack software program written using the Python Programming Language and the Python Qt GUI library"
HOMEPAGE = "https://github.com/savio-code/fern-wifi-cracker"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://core/wps.py;beginline=13;endline=26;md5=a0830c84aa087dd904e22395e93d16c2"

SRCREV = "7434485a50936b63c32ffd72226ffcb55026522f"
SRC_URI = "git://github.com/savio-code/fern-wifi-cracker.git;branch=master;protocol=https \
           file://fern-wifi-cracker \
           "

PV = "2.9+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/Fern-Wifi-Cracker"

RDEPENDS:${PN} = "python3-core python3-pyqt5 python3-sqlite3 python3-json python3-netclient python3-scapy libicui18n xterm subversion macchanger aircrack-ng reaver bash"

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

