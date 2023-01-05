SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"
SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-aarch64-unknown-linux-musl;name=tuc-aarch64;downloadfilename=tuc "
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-x86_64-unknown-linux-musl;name=tuc-x86_64;downloadfilename=tuc "
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-armv7-unknown-linux-musleabihf;name=tuc-arm;downloadfilename=tuc"
SRC_URI[tuc-aarch64.sha256sum] = "5e96a5da1ffde5b19434488e33341c707b86ed88916ecbf4722b31c32699c35c"
SRC_URI[tuc-x86_64.sha256sum] = "77c8f27c2a62ba133237246ff738e4695abe3642a5cf3d67f8e3c98c1ac1f757"
SRC_URI[tuc-arm.sha256sum] = "527fd263dbddb3495e994089f5c6210f4549532a4e9bb5fd2b602ead13179f6a"


INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
