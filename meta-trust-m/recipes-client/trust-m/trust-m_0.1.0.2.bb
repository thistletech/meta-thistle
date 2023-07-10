SUMMARY = "Install the trust-m tooling"
DESCRIPTION = "Install the trust-m tooling"
LICENSE = "MIT"

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
SRC_URI = "gitsm://github.com/Infineon/linux-optiga-trust-m.git;protocol=https;branch=development_v3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc1a86d5b2e9230b6e718647d5ea1252"

DEPENDS = "openssl"
# RDEPENDS_${PN} += " libtrustm.so()(64bit)"

S = "${WORKDIR}/git"

do_compile() {
    cd ${S}
    oe_runmake clean

    if [ "${TARGET_ARCH}" = "aarch64" ]; then
        oe_runmake AARCH64=YES CC="${CC}"
    else
        oe_runmake CC="${CC}"
    fi
}

do_install() {
   install -d ${D}${libdir}
   install -m 0755 ${S}/bin/libtrustm.so ${D}${libdir}
   install -d ${D}${bindir}
   install -m 0755 ${S}/bin/trustm_* ${D}${bindir}
}

FILES_SOLIBSDEV = ""
FILES:${PN} = "${libdir}/libtrustm.so"
FILES:${PN} += "${bindir}/trustm_*"
