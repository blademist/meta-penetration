SUMMARY = "Core tools"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-core-tools \
    packagegroup-core-tools-utils \
    packagegroup-core-tools-extended \
    packagegroup-core-tools-dev-utils \
    packagegroup-core-tools-sys-services \
    "

RDEPENDS:packagegroup-core-tools = "\
    packagegroup-core-tools-utils \
    packagegroup-core-tools-extended \
    packagegroup-core-tools-dev-utils \
    packagegroup-core-tools-sys-services \
    "

RDEPENDS:packagegroup-core-tools-utils = "\
    bash \
    acl \
    attr \
    bc \
    coreutils \
    cpio \
    e2fsprogs \
    ed \
    file \
    findutils \
    gawk \
    grep \
    less \
    makedevs \
    mc \
    mc-shell \
    mc-helpers \
    mc-helpers-perl \
    ncurses \
    net-tools \
    procps \
    psmisc \
    sed \
    tar \
    time \
    util-linux \
    bzip2 \
    cracklib \
    gzip \
    shadow \
    sudo \
    "

RDEPENDS:packagegroup-core-tools-extended = "\
    iproute2 \
    iputils \
    iputils-ping \
    iputils-arping \
    iptables \
    module-init-tools \
    openssl \
    ethtool \
    "

RDEPENDS:packagegroup-core-tools-dev-utils = "\
    diffutils \
    m4 \
    make \
    patch \
    "

RDEPENDS:packagegroup-core-tools-sys-services = "\
    at \
    cronie \
    logrotate \
    nfs-utils \
    rpcbind \
    "
