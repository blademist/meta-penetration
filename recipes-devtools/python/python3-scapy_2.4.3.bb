SUMMARY = "A powerful Python-based interactive packet manipulation program and library"
DESCRIPTION = "Scapy is able to forge or decode packets of a wide number of protocols, send them on the wire, capture them, store or read them using pcap files, match requests and replies, and much more. It is designed to allow fast packet prototyping by using default values that work."
HOMEPAGE = "https://github.com/secdev/scapy"
SECTION = "security"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCNAME = "scapy"

inherit pypi setuptools3

SRC_URI[md5sum] = "469ae6f5615799a5de3f3b4eb217b18d"
SRC_URI[sha256sum] = "e2f8d11f6a941c14a789ae8b236b27bd634681f1b29b5e893861e284d234f6b0"

RDEPENDS:${PN} = "${PYTHON_PN}-compression ${PYTHON_PN}-netclient  ${PYTHON_PN}-netserver \
                  ${PYTHON_PN}-pydoc ${PYTHON_PN}-pkgutil ${PYTHON_PN}-shell \
                  ${PYTHON_PN}-threading ${PYTHON_PN}-numbers ${PYTHON_PN}-pycrypto"

