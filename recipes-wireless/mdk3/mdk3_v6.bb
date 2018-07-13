SUMMARY = "A 802.11 wireless network security testing tool"
HOMEPAGE = "http://aspj.aircrack-ng.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://aspj.aircrack-ng.org/${BPN}-${PV}.tar.bz2 \
           file://0001-Makefile-fix-GNU_HASH-QA-error.patch \
          "
SRC_URI[md5sum] = "67705a814ded2a2e6f70522ca0dc6da9"
SRC_URI[sha256sum] = "4dac4d0ad54c4dbbf8857f527c573af6495a91d2e503774274b39c3ca8ed11dd"

DEPENDS = "libpcap"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "'CC=${CC}' \
                'AR=${AR}' \
                'RANLIB=${RANLIB}' \
                'PREFIX=${prefix}' \
                "

do_install () {
    oe_runmake install DESTDIR=${D}
}

