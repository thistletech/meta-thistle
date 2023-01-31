# Load default bootargs from flattened device tree
fdt addr ${fdt_addr} && fdt get value base_bootargs /chosen bootargs

setenv fit_addr 0x1F000000

# Set A/B boot commands
setenv boot_cmd_a "setenv bootargs '${base_bootargs} root=/dev/mmcblk0p2'; ext4load mmc 0:2 ${fit_addr} /boot/fitImage; bootm ${fit_addr} - ${fdt_addr}"
setenv boot_cmd_b "setenv bootargs '${base_bootargs} root=/dev/mmcblk0p3'; ext4load mmc 0:3 ${fit_addr} /boot/fitImage; bootm ${fit_addr} - ${fdt_addr}"

# Command to source the script to manage dualboot
setexpr custom_scriptaddr ${scriptaddr} + 0x4000
fatload ${devtype} ${devnum}:${distro_bootpart} ${custom_scriptaddr} boot_dualboot.scr
source ${custom_scriptaddr}
