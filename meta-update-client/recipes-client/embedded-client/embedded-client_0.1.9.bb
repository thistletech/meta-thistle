SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"
SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-aarch64-unknown-linux-musl;name=tuc-aarch64;downloadfilename=tuc "
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-x86_64-unknown-linux-musl;name=tuc-x86_64;downloadfilename=tuc "
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-armv7-unknown-linux-musleabihf;name=tuc-arm;downloadfilename=tuc"
SRC_URI[tuc-aarch64.sha256sum] = "6a6b5d4a81e0f5141e32c0ec1a52872737615cde65d8137ebdfd64bde3110b03"
SRC_URI[tuc-x86_64.sha256sum] = "606cfdaecdca51762d4b6ec0c09c661c1c14acc547b380155ab24f74f3c56fbd"
SRC_URI[tuc-arm.sha256sum] = "d718e17f25090e1401c26cf5113681fc6807dabcb72786549959d8ba6e6e5918"


INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
