SUMMARY = "Tools for the Linux Standard Wireless Extension Subsystem"
HOMEPAGE = "https://hewlettpackard.github.io/wireless-tools/Tools.html"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
SECTION = "base"
PE = "1"

SRC_URI = "https://hewlettpackard.github.io/wireless-tools/wireless_tools.${PV}.tar.gz \
           file://remove.ldconfig.call.patch \
           file://man.patch \
           file://avoid_strip.patch \
           file://ldflags.patch \
          "
SRC_URI[md5sum] = "ca91ba7c7eff9bfff6926b1a34a4697d"
SRC_URI[sha256sum] = "abd9c5c98abf1fdd11892ac2f8a56737544fe101e1be27c6241a564948f34c63"

UPSTREAM_CHECK_URI = "https://hewlettpackard.github.io/wireless-tools/Tools.html"
UPSTREAM_CHECK_REGEX = "wireless_tools\.(?P<pver>(\d+)(\..*|))\.tar\.gz"

S = "${WORKDIR}/wireless_tools.30"

CFLAGS =+ "-I${S}"
EXTRA_OEMAKE = "-e 'BUILD_SHARED=y' \
		'INSTALL_DIR=${D}${base_sbindir}' \
		'INSTALL_LIB=${D}${libdir}' \
		'INSTALL_INC=${D}${includedir}' \
		'INSTALL_MAN=${D}${mandir}'"

do_compile() {
	oe_runmake all libiw.a
}

do_install() {
	oe_runmake PREFIX=${D} install-iwmulticall install-dynamic install-man install-hdr
	install -d ${D}${sbindir}
	install -m 0755 ifrename ${D}${sbindir}/ifrename
}

PACKAGES = "libiw libiw-dev libiw-doc ifrename-doc ifrename ${PN} ${PN}-doc ${PN}-dbg"

FILES:libiw = "${libdir}/*.so.*"
FILES:libiw-dev = "${libdir}/*.a ${libdir}/*.so ${includedir}"
FILES:libiw-doc = "${mandir}/man7"
FILES:ifrename = "${sbindir}/ifrename"
FILES:ifrename-doc = "${mandir}/man8/ifrename.8 ${mandir}/man5/iftab.5"
FILES:${PN} = "${bindir} ${sbindir}/iw* ${base_sbindir} ${base_bindir} ${sysconfdir}/network"
FILES:${PN}-doc = "${mandir}"
