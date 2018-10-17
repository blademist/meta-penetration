SUMMARY = "Penetration tools"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-penetration-tools \
    packagegroup-base-tools \
    packagegroup-net-tools \
    packagegroup-wireless-pentools \
    packagegroup-extra-packages \
    "

RDEPENDS_packagegroup-penetration-tools = "\
    packagegroup-base-tools \
    packagegroup-net-tools \
    packagegroup-wireless-pentools \
    packagegroup-extra-packages \
    "

SUMMARY_packagegroup-base-tools = "Base tools"
RDEPENDS_packagegroup-base-tools = "\
    bash \
    coreutils \
    diffutils \
    findutils \
    util-linux \
    e2fsprogs \
    dosfstools \
    less \
    file \
    procps \
    sed \
    gawk \
    grep \
    tar \
    gzip \
    bzip2 \
    ldd \
    tree \
    "

SUMMARY_packagegroup-net-tools = "Network tools"
RDEPENDS_packagegroup-net-tools = "\
    tcpdump \
    netcat \
    wireshark \
    wireshark-gtk \
    tshark \
    nmap \
    macchanger \
    "

SUMMARY_packagegroup-wireless-pentools = "Wireless penetration tools"
RDEPENDS_packagegroup-wireless-pentools = "\
    aircrack-ng \
    pixiewps \
    reaver \
    bully \
    wifite2 \
    mdk3 \
    kismet \
    fern-wifi-cracker \
    "

SUMMARY_packagegroup-extra-packages = "Extra packages"
RDEPENDS_packagegroup-extra-packages = "\
    crda \
    linux-firmware \
    kernel-module-carl9170 \
    "

