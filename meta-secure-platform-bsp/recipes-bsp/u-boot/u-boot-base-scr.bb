DESCRIPTION = "Boot script for support A/B via u-boot script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = " \
    file://boot.cmd \
    file://dualboot.cmd \
"

S = "${WORKDIR}"

inherit deploy

do_compile() {
    mkimage -A arm -T script -C none -n "Thistle base boot script" -d "${WORKDIR}/boot.cmd" boot.scr
    mkimage -A arm -T script -C none -n "Thistle base boot script" -d "${WORKDIR}/dualboot.cmd" boot_dualboot.scr
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 boot.scr ${DEPLOYDIR}/boot.scr
    install -m 0644 boot_dualboot.scr ${DEPLOYDIR}/boot_dualboot.scr
}

addtask do_deploy after do_compile before do_build

PROVIDES += "u-boot-default-script"
