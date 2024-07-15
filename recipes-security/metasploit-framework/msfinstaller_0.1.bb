SUMMARY = "A script to install metasploit-framework"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://msfinstaller.sh \
           "

RDEPENDS:${PN} = "packagegroup-core-buildessential \
                  ruby ruby-dev \
                  libxml2 libxml2-dev \
                  libxslt libxslt-dev \
                  libpq libpq-dev \
                  libpcap libpcap-dev \
                  bash curl ncurses-tools \
                  postgresql postgresql-client \
                 "

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/msfinstaller.sh ${D}${bindir}/msfinstaller.sh
}

INSANE_SKIP:${PN} += "dev-deps"
