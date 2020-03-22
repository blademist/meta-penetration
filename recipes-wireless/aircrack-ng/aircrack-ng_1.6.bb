SUMMARY = "A set of tools for auditing wireless networks"
HOMEPAGE = "http://www.aircrack-ng.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=014976fd8a05c5e5b5a38415d8383af1"

SRC_URI = "http://dl.aircrack-ng.org/${BPN}-${PV}.tar.gz \
          "

SRC_URI[md5sum] = "22ddc85549b51ed0da0931d01ef215e5"
SRC_URI[sha256sum] = "4f0bfd486efc6ea7229f7fbc54340ff8b2094a0d73e9f617e0a39f878999a247"

inherit autotools pkgconfig

DEPENDS = "libnl openssl libpcap sqlite3 libpcre"
RDEPENDS_${PN} = "bash coreutils procps ethtool iw"

PACKAGECONFIG ??= "hwloc"
PACKAGECONFIG[hwloc] = "--enable-hwloc,--disable-hwloc,hwloc"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libaircrack*.so"

INSANE_SKIP_${PN} = "dev-so"
