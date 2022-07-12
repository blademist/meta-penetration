SUMMARY = "A set of tools for auditing wireless networks"
HOMEPAGE = "http://www.aircrack-ng.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=014976fd8a05c5e5b5a38415d8383af1"

SRC_URI = "git://github.com/aircrack-ng/aircrack-ng.git;branch=master;protocol=https"
SRCREV = "048e434b7d0f7f9084d0e0f7803e92dab426f6e3"
PV = "1.6+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "libnl openssl libpcap sqlite3 libpcre"
RDEPENDS:${PN} = "bash coreutils procps ethtool iw"

PACKAGECONFIG ??= "hwloc"
PACKAGECONFIG[hwloc] = "--enable-hwloc,--disable-hwloc,hwloc"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/libaircrack*.so"

INSANE_SKIP:${PN} = "dev-so"
