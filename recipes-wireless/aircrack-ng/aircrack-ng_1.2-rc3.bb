SUMMARY = "A set of tools for auditing wireless networks"
HOMEPAGE = "http://www.aircrack-ng.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

SRC_URI = "http://dl.aircrack-ng.org/${BPN}-${PV}.tar.gz \
           file://fix_libsql.patch \
          "

SRC_URI[md5sum] = "3f4096808c916baa8872a5ad138a9c35"
SRC_URI[sha256sum] = "8e595ccab9fe90fc7c770634ad13e30da2d7fef21497b34d56d7014b991a4585"

inherit pkgconfig

DEPENDS = "libnl openssl libpcap sqlite3 libpcre"
RDEPENDS_${PN} = "bash coreutils procps ethtool wireless-tools"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "prefix=${prefix} \
                libdir=${libdir} \
                etcdir=${sysconfdir}/${BPN} \
                sqlite=true \
                pcre=true \
                "

do_install () {
    oe_runmake install DESTDIR=${D}
}

