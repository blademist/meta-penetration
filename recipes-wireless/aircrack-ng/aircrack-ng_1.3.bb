SUMMARY = "A set of tools for auditing wireless networks"
HOMEPAGE = "http://www.aircrack-ng.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

SRC_URI = "http://dl.aircrack-ng.org/${BPN}-${PV}.tar.gz \
          "

SRC_URI[md5sum] = "c7c5b076dee0c25ee580b0f56f455623"
SRC_URI[sha256sum] = "8ae08a7c28741f6ace2769267112053366550e7f746477081188ad38410383ca"

inherit autotools pkgconfig

DEPENDS = "libnl openssl libpcap sqlite3 libpcre"
RDEPENDS_${PN} = "bash coreutils procps ethtool iw"

