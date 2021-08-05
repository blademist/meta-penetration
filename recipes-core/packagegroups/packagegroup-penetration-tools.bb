SUMMARY = "Penetration tools"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-penetration-tools \
    packagegroup-base-tools \
    packagegroup-net-tools \
    packagegroup-wireless-pentools \
    packagegroup-security-tools \
    "

RDEPENDS:packagegroup-penetration-tools = "\
    packagegroup-base-tools \
    packagegroup-net-tools \
    packagegroup-wireless-pentools \
    packagegroup-security-tools \
    "

SUMMARY:packagegroup-base-tools = "Base tools"
RDEPENDS:packagegroup-base-tools = "\
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

SUMMARY:packagegroup-net-tools = "Network tools"
RDEPENDS:packagegroup-net-tools = "\
    tcpdump \
    netcat \
    wireshark \
    tshark \
    nmap \
    macchanger \
    "

SUMMARY:packagegroup-wireless-pentools = "Wireless penetration tools"
RDEPENDS:packagegroup-wireless-pentools = "\
    aircrack-ng \
    pixiewps \
    reaver \
    bully \
    wifite2 \
    mdk3 \
    kismet \
    fern-wifi-cracker \
    "

SUMMARY:packagegroup-extra-packages = "Extra packages"
RDEPENDS:packagegroup-extra-packages = "\
    crda \
    linux-firmware \
    kernel-module-carl9170 \
    "

SUMMARY:packagegroup-security-tools = "Security tools"
RDEPENDS:packagegroup-security-tools = "\
    msfinstaller \
    "
