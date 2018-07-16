SUMMARY = "A open source utility for network discovery and security auditing"
HOMEPAGE = "https://www.nmap.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892ecc57ee9a91d049fc49f0914269a0"

SRC_URI = "https://nmap.org/dist/${BPN}-${PV}.tar.bz2 \
           file://0001-Makefile.in-set-default-python-path.patch \
          "
SRC_URI[md5sum] = "84eb6fbe788e0d4918c2b1e39421bf79"
SRC_URI[sha256sum] = "847b068955f792f4cc247593aca6dc3dc4aae12976169873247488de147a6e18"

DEPENDS = "openssl libpcap libpcre"

inherit autotools-brokensep gettext python-dir

EXTRA_AUTORECONF += "--exclude=autoheader,automake"

PACKAGECONFIG ??= "lua nping ncat"
PACKAGECONFIG[lua] = "--with-liblua=included, --without-liblua, , "
PACKAGECONFIG[ndiff] = "--with-ndiff, --without-ndiff, , python python-difflib python-xml"
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

