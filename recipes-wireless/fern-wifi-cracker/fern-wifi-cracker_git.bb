SUMMARY = "A wireless security auditing and attack software program written using the Python Programming Language and the Python Qt GUI library"
HOMEPAGE = "https://github.com/savio-code/fern-wifi-cracker"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://core/wps.py;beginline=13;endline=26;md5=a0830c84aa087dd904e22395e93d16c2"

SRCREV = "df0ef358c3a949279443111d02108c5c9aa3b190"
SRC_URI = "git://github.com/savio-code/fern-wifi-cracker.git \
           file://fern-wifi-cracker \
           "

PV = "2.7+git${SRCPV}"

inherit allarch

S = "${WORKDIR}/git/Fern-Wifi-Cracker"

RDEPENDS_${PN} = "python3-core python3-pyqt5 python3-sqlite3 python3-json python3-netclient python3-scapy libicui18n xterm subversion macchanger aircrack-ng reaver bash"

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

