SUMMARY = "Hardened memory allocator"
DESCRIPTION = "Hardened memory allocator from the musl project"
LICENSE = "CLOSED"
SRC_URI = "https://downloads.thistle.tech/thistle-sdk/${PV}/tsp-${PV}.tar.gz"
SRC_URI[sha256sum] = "19bf2a1e4ba14381cdb0808f1079aed127af537bb1de31624fa84e852b6ba9df"

INSANE_SKIP:${PN} += "already-stripped"
S = "${WORKDIR}/tsp-${PV}"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${sysconfdir}

    cp --no-dereference ${S}/lib/${TARGET_ARCH}/libmallocng.so*  ${D}${libdir}
    echo  "${libdir}/libmallocng.so.0.0.0" > ${D}${sysconfdir}/ld.so.preload
}