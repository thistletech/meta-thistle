SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"

SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-aarch64-unknown-linux-musl;name=tuc-aarch64;downloadfilename=tuc "
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-x86_64-unknown-linux-musl;name=tuc-x86_64;downloadfilename=tuc "
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-arm-unknown-linux-musleabihf;name=tuc-arm;downloadfilename=tuc"
SRC_URI[tuc-aarch64.sha256sum] = "3c45c984bafbcf9ca7d0fee033bfe693a58534ad63efcb318872bdbfce569252"
SRC_URI[tuc-x86_64.sha256sum] = "7f1103ce2006b921256bddc0fa30a69e379646fbb4d7dac3ed536f701abba39b"
SRC_URI[tuc-arm.sha256sum] = "0d9acf62eafd129428ac5a6b33f3c3415ef22585bd5c78faf74ba4d493ac5e70"

INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
