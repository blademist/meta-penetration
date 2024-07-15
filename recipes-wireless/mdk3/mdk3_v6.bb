SUMMARY = "A 802.11 wireless network security testing tool"
HOMEPAGE = "http://aspj.aircrack-ng.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://aspj.aircrack-ng.org/${BPN}-${PV}.tar.bz2 \
           file://0001-Makefile-fix-GNU_HASH-QA-error.patch \
           file://0001-mdk3.c-fix-format-string-for-printf.patch \
           file://0001-Makefile-pass-correct-CFLAGS-for-cross-compilation.patch \
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

# Fix build with GCC-14 which enables incompatible-pointer-types as error
CFLAGS += "-Wno-error=incompatible-pointer-types"

do_install () {
    oe_runmake install DESTDIR=${D}
}

