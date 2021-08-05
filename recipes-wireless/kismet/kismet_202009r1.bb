SUMMARY = "An 802.11 layer2 wireless network detector, sniffer, and intrusion detection system"
HOMEPAGE = "https://www.kismetwireless.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=21d24e9d61cfe0fd83aebabd3788785c"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-2020-09-R1.tar.xz"

SRC_URI[md5sum] = "1872a725b4bf4328b7856fb97ca711fd"
SRC_URI[sha256sum] = "3ad794c341e3aabdeea6d20b51bf35be913148704077532d177b66629aa39128"

S = "${WORKDIR}/${BPN}-2020-09-R1"

DEPENDS = "libnl libpcap libmicrohttpd sqlite3 protobuf protobuf-native protobuf-c protobuf-c-native libusb1"
RDEPENDS:${PN} = "gpsd wireshark"

PACKAGECONFIG ??= "python-tools"
PACKAGECONFIG[python-tools] = "--enable-python-tools,--disable-python-tools,python3-setuptools-native python3-protobuf-native python3-numpy-native python3-pyserial-native,python3-protobuf python3-numpy python3-pyserial"

inherit autotools-brokensep pkgconfig
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python-tools', 'python3native', '', d)}

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/${BPN} \
               "

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'python-tools', 'true', 'false', d)}; then
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_freaklabs_zigbee
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtl433
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtladsb
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtlamr
    fi
}

PACKAGES =+ "${@bb.utils.contains('PACKAGECONFIG', 'python-tools', '${PN}-capfreaklabszigbee ${PN}-capsdrrtl433 ${PN}-capsdrrtladsb ${PN}-capsdrrtlamr', '', d)}"

FILES:${PN}-capfreaklabszigbee = "${bindir}/kismet_cap_freaklabs_zigbee \
                                  ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureFreaklabsZigbee* \
                                 "
FILES:${PN}-capsdrrtl433 = "${bindir}/kismet_cap_sdr_rtl433 \
                            ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtl433* \
                           "
FILES:${PN}-capsdrrtladsb = "${bindir}/kismet_cap_sdr_rtladsb \
                             ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtladsb* \
                            "
FILES:${PN}-capsdrrtlamr = "${bindir}/kismet_cap_sdr_rtlamr \
                            ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtlamr* \
                           "
