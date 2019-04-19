//
//  TableViewCell.swift
//  AdamInfiesto_Research
//
//  Created by Adam Infiesto on 9/28/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {

    @IBOutlet weak var daTitle: UILabel!
    @IBOutlet weak var subTitle: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
