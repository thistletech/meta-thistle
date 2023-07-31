FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SRC_URI:append:qemuarm64 = " file://qemuarm64.dts "
KERNEL_DEVICETREE:qemuarm64 = "qemuarm64.dtb"

SRC_URI += "file://scsi_support.cfg \
            file://vfat_support.cfg"

do_configure:qemuarm64:append() {
    cp ${WORKDIR}/qemuarm64.dts ${S}/arch/arm64/boot/dts
}