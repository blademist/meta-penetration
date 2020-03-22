SUMMARY = "An 802.11 layer2 wireless network detector, sniffer, and intrusion detection system"
HOMEPAGE = "https://www.kismetwireless.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=21d24e9d61cfe0fd83aebabd3788785c"

SRC_URI = "https://www.kismetwireless.net/code/${BPN}-2020-03-R1.tar.xz \
           "

SRC_URI[md5sum] = "863be883e2582ebae7ef17e9c8ddfd5e"
SRC_URI[sha256sum] = "034db8a02b5bb93bfd3f22b855faa774a212f091b6a6824c7e33aa44744d3fd6"

S = "${WORKDIR}/${BPN}-2020-03-R1"

DEPENDS = "libnl libpcap libmicrohttpd sqlite3 protobuf protobuf-native protobuf-c protobuf-c-native libusb1"
RDEPENDS_${PN} = "gpsd wireshark"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/${BPN} \
                --disable-python-tools \
               "
