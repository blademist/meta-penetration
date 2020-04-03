SUMMARY = "An 802.11 layer2 wireless network detector, sniffer, and intrusion detection system"
HOMEPAGE = "https://www.kismetwireless.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=21d24e9d61cfe0fd83aebabd3788785c"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-2020-03-R1.tar.xz \
           file://0001-python-tools-Fix-installation-for-python-site-packag.patch \
           "

SRC_URI[md5sum] = "863be883e2582ebae7ef17e9c8ddfd5e"
SRC_URI[sha256sum] = "034db8a02b5bb93bfd3f22b855faa774a212f091b6a6824c7e33aa44744d3fd6"

S = "${WORKDIR}/${BPN}-2020-03-R1"

DEPENDS = "libnl libpcap libmicrohttpd sqlite3 protobuf protobuf-native protobuf-c protobuf-c-native libusb1"
RDEPENDS_${PN} = "gpsd wireshark"

PACKAGECONFIG ??= "python-tools"
PACKAGECONFIG[python-tools] = "--enable-python-tools,--disable-python-tools,python3-setuptools-native python3-protobuf-native python3-numpy-native python3-pyserial-native,python3-protobuf python3-numpy python3-pyserial"

inherit autotools-brokensep pkgconfig
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python-tools', 'python3native', '', d)}

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/${BPN} \
               "

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'python-tools', 'true', 'false', d)}; then
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_freaklabs_zigbee
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtl433
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtladsb
        sed -i -e '1s,#!.*python.*,#!${USRBINPATH}/env python3,' ${D}${bindir}/kismet_cap_sdr_rtlamr
    fi
}

PACKAGES =+ "${@bb.utils.contains('PACKAGECONFIG', 'python-tools', '${PN}-capfreaklabszigbee ${PN}-capsdrrtl433 ${PN}-capsdrrtladsb ${PN}-capsdrrtlamr', '', d)}"

FILES_${PN}-capfreaklabszigbee = "${bindir}/kismet_cap_freaklabs_zigbee \
                                  ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureFreaklabsZigbee* \
                                 "
FILES_${PN}-capsdrrtl433 = "${bindir}/kismet_cap_sdr_rtl433 \
                            ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtl433* \
                           "
FILES_${PN}-capsdrrtladsb = "${bindir}/kismet_cap_sdr_rtladsb \
                             ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtladsb* \
                            "
FILES_${PN}-capsdrrtlamr = "${bindir}/kismet_cap_sdr_rtlamr \
                            ${libdir}/python${PYTHON_BASEVERSION}/site-packages/KismetCaptureRtlamr* \
                           "
