SUMMARY = "A set of tools for auditing wireless networks"
HOMEPAGE = "http://www.aircrack-ng.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

SRC_URI = "http://dl.aircrack-ng.org/${BPN}-${PV}.tar.gz \
           file://fix_libsql.patch \
          "

SRC_URI_append_arm = " file://common_cfg_arm.patch"

SRC_URI[md5sum] = "3bbc7d5035a98ec01e78774d05c3fcce"
SRC_URI[sha256sum] = "d93ac16aade5b4d37ab8cdf6ce4b855835096ccf83deb65ffdeff6d666eaff36"

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

