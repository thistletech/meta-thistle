# Set A/B boot commands
setenv boot_cmd_a "ext4load scsi 0:2 ${kernel_addr_r} /boot/fitImage; setenv bootargs "root=/dev/sda2 rw mem=256M console=ttyAMA0"; bootm ${kernel_addr_r} - 40000000"
setenv boot_cmd_b "ext4load scsi 0:3 ${kernel_addr_r} /boot/fitImage; setenv bootargs "root=/dev/sda3 rw mem=256M console=ttyAMA0"; bootm ${kernel_addr_r} - 40000000"

# Command to source the script to manage dualboot
setexpr custom_scriptaddr ${scriptaddr} + 0x4000
fatload ${devtype} ${devnum}:${distro_bootpart} ${custom_scriptaddr} boot_dualboot.scr
source ${custom_scriptaddr}