SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"

SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-aarch64-unknown-linux-musl.gz;name=tuc-aarch64;downloadfilename=tuc.gz"
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-x86_64-unknown-linux-musl.gz;name=tuc-x86_64;downloadfilename=tuc.gz"
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-arm-unknown-linux-musleabihf.gz;name=tuc-arm;downloadfilename=tuc.gz"
SRC_URI[tuc-aarch64.sha256sum] = "afabf8ce9d3e112245d7eaf420e1494a4f3d68255d234d712e53ff930937b279"
SRC_URI[tuc-x86_64.sha256sum] = "b7d4213bdb551af892716f9973407dedc8df165e7f7d0a4294a891d5566e41b1"
SRC_URI[tuc-arm.sha256sum] = "cae42110ea3f47585775f7e318b8409a28a52060f10e699795ffa0c6ccbbc2e9"

INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
