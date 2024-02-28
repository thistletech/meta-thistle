SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"

SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-aarch64-unknown-linux-musl.gz;name=tuc-aarch64;downloadfilename=tuc.gz"
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-x86_64-unknown-linux-musl.gz;name=tuc-x86_64;downloadfilename=tuc.gz"
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-arm-unknown-linux-musleabihf.gz;name=tuc-arm;downloadfilename=tuc.gz"
SRC_URI[tuc-aarch64.sha256sum] = "e2521cfc8f9789ee484d6dc879e18111d8aac6089616d411af5bb3452d457ffa"
SRC_URI[tuc-x86_64.sha256sum] = "3fa2f4581305f3b7ec9aee52296cf26b4ad2e7f64bb33d8f3a489ad05fdd8753"
SRC_URI[tuc-arm.sha256sum] = "e975bf7759af089db8c072d37827c48d0f09d227c5ffc74718370aada6354b00"

INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
