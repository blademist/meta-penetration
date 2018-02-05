SUMMARY = "A open source utility for network discovery and security auditing"
HOMEPAGE = "https://www.nmap.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892ecc57ee9a91d049fc49f0914269a0"

SRC_URI = "https://nmap.org/dist/${BPN}-${PV}.tar.bz2 \
           file://0001-Makefile.in-set-default-python-path.patch \
          "
SRC_URI[md5sum] = "7fa4edc592184c7addc14f5acb3fe6f7"
SRC_URI[sha256sum] = "cf1fcd2643ba2ef52f47acb3c18e52fa12a4ae4b722804da0e54560704627705"

DEPENDS = "openssl libpcap libpcre"

inherit autotools-brokensep gettext python-dir

EXTRA_AUTORECONF += "--exclude=autoheader,automake"

PACKAGECONFIG ??= "lua ndiff nping ncat"
PACKAGECONFIG[lua] = "--with-liblua=included, --without-liblua, , "
PACKAGECONFIG[ndiff] = "--with-ndiff, --without-ndiff, , python python-difflib"
PACKAGECONFIG[nping] = "--with-nping, --without-nping, , "
PACKAGECONFIG[ncat] = "--with-ncat, --without-ncat, , "
PACKAGECONFIG[zenmap] = "--with-zenmap, --without-zenmap, , python python-unittest python-doctest"
PACKAGECONFIG[subversion] = "--with-subversion, --without-subversion, , subversion"

do_configure_append () {
    cd ${S}/libdnet-stripped/
    autoreconf -Wcross --verbose --install --force
    cd ${S}
}

PACKAGES += "${@bb.utils.contains("PACKAGECONFIG", "zenmap", "${PN}-zenmap", "", d)}"

FILES_${PN} = "${bindir}/nmap \
               ${bindir}/ncat \
               ${bindir}/ndiff \
               ${bindir}/nping \
               ${bindir}/uninstall_ndiff \
               ${datadir}/ncat* \
               ${datadir}/nmap* \
               ${libdir}/python${PYTHON_BASEVERSION}/site-packages/ndiff* \
              "

FILES_${PN}-zenmap = "${@bb.utils.contains("PACKAGECONFIG", "zenmap", \
                     "${bindir}/*zenmap \
                     ${bindir}/xnmap \
                     ${bindir}/nmapfe \
                     ${datadir}/zenmap/* \
                     ${datadir}/applications/* \
                     ${libdir}/python${PYTHON_BASEVERSION}/site-packages/radialnet/* \
                     ${libdir}/python${PYTHON_BASEVERSION}/site-packages/zenmap* \
                     ", "", d)}"

RDEPENDS_${PN}-zenmap = "nmap"

