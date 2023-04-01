SUMMARY = "Install the update client binary"
DESCRIPTION = "Install the update client binary"
LICENSE = "CLOSED"

SRC_URI:aarch64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-aarch64-unknown-linux-musl;name=tuc-aarch64;downloadfilename=tuc "
SRC_URI:x86_64 = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-x86_64-unknown-linux-musl;name=tuc-x86_64;downloadfilename=tuc "
SRC_URI:arm = "https://downloads.thistle.tech/embedded-client/${PV}/tuc-${PV}-armv7-unknown-linux-musleabihf;name=tuc-arm;downloadfilename=tuc"
SRC_URI[tuc-aarch64.sha256sum] = "2380d831b8dc03cd5a674bfa08231d3f91ed40785aabcf290120f9ff6889cd8a"
SRC_URI[tuc-x86_64.sha256sum] = "a3dddad5162370b99b6449380ad9c0d2f2fa7f853678204b761a3ae66a95662a"
SRC_URI[tuc-arm.sha256sum] = "837c6ff23f96a1045e7a2683bb70c2b20a66efa97ecaae29749795c50c073131"

INSANE_SKIP:${PN} += "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/tuc ${D}${bindir}/tuc
}
